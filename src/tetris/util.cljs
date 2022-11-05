(ns tetris.util)

(defn get-clear-playfield [x y]
  (repeat y (repeat x 0)))
