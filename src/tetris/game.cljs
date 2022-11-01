(ns tetris.game
  (:require [tetris.blocks :as blocks]))

(def actions
  [:left
   :right
   :down
   :rotate
   :drop
   :hold
   :tick])

(defn get-clear-playfield [x y]
  (repeat y (repeat x 0)))

(defn can-move? [playfield block x y]
  ;; from [][] -> {:x :y :value}[]
  (let [cells (for [[i row] (map-indexed list block)
                    [j cell] (map-indexed list row)]
                {:x i :y j :value cell})]
    ;; If we have no overlapping block/oob, we can move!
    (every?
     true?
     (map
      ;; TODO: use reduce instead of map
      (fn [cell]
        (let [new-y (+ y (:y cell))
              new-x (+ x (:x cell))]
          (cond
            ;; TODO: Handle case x or y < 0
            ;; Return false if out of bounds y
            (>= new-y (count playfield)) false
            ;; Return false if out of bounds x
            (>= new-x (count (nth playfield new-y))) false
            ;; Return true if there is no block on the playfield
            (> 1 (nth (nth playfield new-y) new-x)) true
            :else false)))
      (filter (fn [cell] (> (:value cell) 0)) cells)))))

(comment
  (let [x 1
        y 1
        block [[1 0 0 0]
               [1 0 0 0]
               [1 0 0 0]
               [1 0 0 0]]
        playfield (get-clear-playfield 11 20)]
    (can-move? playfield block x y)))


;; get block history
(defn tick [playfield action block x y]
  (case action
    :rotate (let [new-block (blocks/rotate-block block)]
              (if (can-move? playfield new-block x y)
                [playfield new-block x y]
                ;; TODO: Try to shimmy piece
                [playfield block x y]))
    :left (if (can-move? playfield block (dec x) y)
            [playfield block (dec x) y]
            [playfield block x y])
    :right (if (can-move? playfield block x (inc y))
             [playfield block x (inc y)]
             [playfield block x y])
    :down (if (can-move? playfield block x (inc y))
            [playfield block x (inc y)]
            [playfield block x y])
    ;; default case: do nothing
    [playfield block x y]
    ;; TODO:
    ;; :drop
    ;; :hold
    ;; :tick  (check if lines are completed & generate new block)
    )
  )

