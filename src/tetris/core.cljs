(ns ^:figwheel-hooks tetris.core
  (:require [goog.dom :as gdom]
            [tetris.inputs :as input]
            [tetris.tetromino :as tetromino]
            [tetris.util :as util]
            [tetris.game :as game]
            [tetris.visuals :as visuals]))

(def
  app-state
  (atom
   {:playfield (util/get-clear-playfield 11 20)
    :block  tetromino/l-block
    :x 2
    :y 0
    :tools (visuals/get-tools)
    :config (visuals/get-config)}))

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
     (visuals/draw-state
      (swap! app-state merge (game/tick (assoc @app-state :action action)))))))

(comment
  (visuals/draw-state @app-state))

