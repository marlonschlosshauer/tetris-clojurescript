(ns tetris.inputs)

(defn on-input [key]
  (case key
    ("k" "K" "w" "W" "ArrowUp") :rotate
    ("h" "H" "a" "A" "ArrowLeft") :left
    ("l" "L" "d" "D" "ArrowRight") :right
    ("j" "J" "s" "S" "ArrowDown") :down
    (" ") :drop
    :tick))

