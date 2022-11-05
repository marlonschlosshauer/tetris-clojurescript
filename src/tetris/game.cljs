(ns tetris.game
  (:require [tetris.blocks :as blocks]
            [tetris.util :as util]))

(def actions
  [:left
   :right
   :down
   :rotate
   :drop
   :hold
   :tick])

(defn can-move? [{playfield :playfield
                  block :block
                  x :x
                  y :y}]
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
  ;; Run `can-move?`
  (can-move?
   {:x 1
    :y 1
    :block [[1 0 0 0]
            [1 0 0 0]
            [1 0 0 0]
            [1 0 0 0]]
    :playfield (util/get-clear-playfield 11 20)}))


;; get block history
(defn tick [data]
  ;; TODO: check if lines are completed
  ;; TODO: generate new block (on drop)
  ;; TODO: also get score (?)
  ;; TODO:
  ;; :drop
  ;; :hold
  ;; :tick
  (let [new-data
        (case (:action data)
          :rotate {:block (blocks/rotate-block (:block data))}
          :left {:x (dec (:x data))}
          :right {:x (inc (:x data))}
          :down {:y (inc (:y data))}
          data)]
    (merge data new-data)))


(comment
  ;; Run `tick`
  (tick
   {:action :down
    :x 1
    :y 1
    :block [[1 0 0 0]
            [1 0 0 0]
            [1 0 0 0]
            [1 0 0 0]]
    :playfield (util/get-clear-playfield 11 20)}))
