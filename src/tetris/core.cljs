(ns ^:figwheel-hooks tetris.core
  (:require [goog.dom :as gdom]
            [tetris.inputs :as input]))

(defonce app-state (atom {:text "Hello world!"}))

(defn capture-input [& cb]
  (. js/window
     addEventListener
     "keydown"
     (if (nil? cb)
       (fn [e] (input/on-input (.-key e)))
       (fn [e] (cb (input/on-input (.-key e)))))))

;; How to run it on start though?
(defn ^:after-load start []
  (println (. js/Date now))
  (capture-input println))
