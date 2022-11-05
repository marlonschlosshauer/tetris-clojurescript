(ns tetris.visuals
  (:require
   [goog.dom :as gdom]
   [tetris.util :as util]
   [tetris.tetromino :as tetromino]))

(defn get-tools []
  (let [canvas (gdom/getElement "playground")]
    {:canvas canvas :brush (. canvas getContext "2d")}))

(defn get-config []
  {:width 11
   :height 20
   :initial-x 4
   :initial-y 0
   :block-width 16
   :border-width 2
   :color-full "#bfcd99"
   :color-empty "#000000"
   :color-ghost "#373837"})

(defn draw-block
  [{tools :tools
    x :x y :y
    type :type
    {border-width :border-width
     block-width :block-width
     color-full :color-full
     color-empty :color-empty
     color-ghost :color-ghost} :config, :as all}]
  (set!
   (.-strokeStyle (:brush tools))
   (case type
     :full color-full
     :empty color-empty
     :ghost color-ghost))
  (set! (.-fillStyle (:brush tools))
        (case type
          :full color-full
          :empty color-empty
          :ghost color-ghost))
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
  all)

(comment
  ;; Run `draw-block`
  (draw-block
   {:tools (get-tools)
    :x 0 :y 0
    :type :full
    :config (get-config)}))

(defn draw-blocks
  [{x :x
    y :y
    block :block, :as all}]
  (doseq [[i row] (map-indexed list block)
        [j cell] (map-indexed list row)]
    (draw-block
     (merge
      all
      {:x (+ x i) :y (+ y j)
       :type (if (zero? cell) :empty :full)})))
  all)

(comment
  ;; Run `draw-blocks`
  (draw-blocks
   {:x 0 :y 0
    :block tetromino/i-block
    :config (get-config)
    :tools (get-tools)}))

(defn draw-state [state]
  ;; Draw playfield
  (draw-blocks (merge state {:x 0 :y 0} {:block (:playfield state)}))
  ;; Draw block
  (draw-blocks state)
  ;; Return state
  state)


(comment
  ;; Run `draw-state`
  (draw-state
   {:playfield (util/get-clear-playfield 11 20)
    :block  tetromino/j-block
    :x 2
    :y 0
    :tools (get-tools)
    :config (get-config)}))


;; TODO: Update to use {} instead of []
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

