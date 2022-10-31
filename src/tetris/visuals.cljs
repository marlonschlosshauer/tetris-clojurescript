(ns tetris.visuals
  (:require
   [goog.dom :as gdom]
   [tetris.tetromino :as tetromino]))

(defn get-tools []
  (let [canvas (gdom/getElement "playground")]
    {:canvas canvas :brush (. canvas getContext "2d")}))

(defn get-config []
  {:width 11
   :height 20
   :initial-x 4
   :initial-y 0
   :block-size 16
   :block-border 2
   :color-full "#bfcd99"
   :color-empty "#000000"
   :color-ghost "#373837"})

(defn draw-raw-square [tools [x y] [block-width block-color] [border-width border-color]]
  (set! (.-strokeStyle (:brush tools)) border-color)
  (set! (.-fillStyle (:brush tools)) block-color)
  (.
   (:brush tools)
   fillRect
   (+ (* x (+ block-width border-width border-width)) (* 2 border-width))
   (+ (* y (+ block-width border-width border-width)) (* 2 border-width))
   (- block-width (* 2 border-width))
   (- block-width (* 2 border-width)))
  (.
   (:brush tools)
   strokeRect
   (+ (* x (+ block-width border-width border-width)) border-width)
   (+ (* y (+ block-width border-width border-width)) border-width)
   block-width
   block-width)
  tools)

(defn draw-square
  [tools
   x y
   type
   {block-size :block-size
    block-border :block-border
    color-full :color-full
    color-empty :color-empty
    color-ghost :color-ghost}]
  (draw-raw-square
   tools
   [x y]
   [block-size
    (case type
      :full color-full
      :empty color-empty
      :ghost color-ghost)]
   [block-border
    (case type
      :full color-full
      :empty color-empty
      :ghost color-ghost)])
  tools)

(comment
  ;; Run `draw-square`
  (draw-square (get-tools) 0 0 :full (get-config))
  (draw-square (get-tools) 0 0 :ghost (get-config)))

(defn draw-block [tools  x y type block config]
  (map
   (fn
     [cell]
     (draw-square
      tools
      (+ x (:x cell))
      (+ y (:y cell))
      (if (= (:value cell) 1)
        :full
        :empty)
      config))
   ;; TODO: Check if i j values are correct (blocks are drawn transposed)
   (for [[i row] (map-indexed list block)
         [j cell] (map-indexed list row)]
     {:x i :y j :value cell}))
  tools)

(comment
  ;; Run `draw-block`
  (draw-block
   (get-tools)
   0 0 :full
   tetromino/i-block
   (get-config)))


(defn update-playground-size
  [tools
   {width :width
    height :height
    block :block-size
    border :block-border}]
  (let [canvas (:canvas tools)
        height (* height (+ block (* 2 border)))
        width (* width (+ block (* 2 border)))]
    (set! (.-height canvas) height)
    (set! (.-width canvas) height)
    (set! (.-height (.-style  canvas)) (str height "px"))
    (set! (.-width (.-style  canvas)) (str width "px"))
    [width height]))

(comment
  ;; Run `update-playground-size`
  (update-playground-size (get-tools) (get-config)))
