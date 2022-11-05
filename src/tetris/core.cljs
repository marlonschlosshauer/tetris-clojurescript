(ns ^:figwheel-hooks tetris.core
  (:require [goog.dom :as gdom]
            [tetris.inputs :as input]
            [tetris.tetromino :as tetromino]
            [tetris.game :as game]))

(defonce
  app-state
  (atom
   {:playfield
    [[0 0 0 0 0 0 0 0]
     [0 0 0 0 0 0 0 0]
     [0 0 0 0 0 0 0 0]
     [0 0 0 0 0 0 0 0]
     [0 0 0 0 0 0 0 0]
     [0 0 0 0 0 0 0 0]
     [0 0 0 0 0 0 0 0]
     [0 0 0 0 0 0 0 0]]
    :block  tetromino/l-block
    :x 2
    :y 0}))

(defn capture-input [cb]
  (. js/window
     addEventListener
     "keydown"
     (fn [e] (cb (input/on-input (.-key e))))))

;; How to run it on start though?
(defn ^:after-load start []
  (println (. js/Date now))
  (capture-input
   (fn [action]
     (swap! app-state merge (game/tick (assoc @app-state :action action))))))
