(ns tetris.tetromino)

(def z-block
  [[1 1 0 0]
   [0 1 1 0]
   [0 0 0 0]
   [0 0 0 0]])

(def z-block-rotated
  [[0 1 0 0]
   [1 1 0 0]
   [1 0 0 0]
   [0 0 0 0]])

(def s-block
  [[0 1 1 0]
   [1 1 0 0]
   [0 0 0 0]
   [0 0 0 0]])

(def s-block-rotated
  [[1 0 0 0]
   [1 1 0 0]
   [0 1 0 0]
   [0 0 0 0]])

(def t-block
  [[1 1 1 0]
   [0 1 0 0]
   [0 0 0 0]
   [0 0 0 0]])

(def t-block-rotated-1
  [[0 1 0 0]
   [1 1 0 0]
   [0 1 0 0]
   [0 0 0 0]])

(def t-block-rotated-2
  [[0 1 0 0]
   [1 1 1 0]
   [0 0 0 0]
   [0 0 0 0]])

(def t-block-rotated-3
  [[0 1 0 0]
   [0 1 1 0]
   [0 1 0 0]
   [0 0 0 0]])

(def o-block
  [[1 1 0 0]
   [1 1 0 0]
   [0 0 0 0]
   [0 0 0 0]])

(def l-block
  [[1 0 0 0]
   [1 0 0 0]
   [1 1 0 0]
   [0 0 0 0]])

(def l-block-rotated-1
  [[1 1 1 0]
   [1 0 0 0]
   [0 0 0 0]
   [0 0 0 0]])

(def l-block-rotated-2
  [[1 1 0 0]
   [0 1 0 0]
   [0 1 0 0]
   [0 0 0 0]])

(def l-block-rotated-3
  [[0 0 1 0]
   [1 1 1 0]
   [0 0 0 0]
   [0 0 0 0]])

(def j-block
  [[0 1 0 0]
   [0 1 0 0]
   [1 1 0 0]
   [0 0 0 0]])

(def j-block-rotated-1
  [[1 0 0 0]
   [1 1 1 0]
   [0 0 0 0]
   [0 0 0 0]])

(def j-block-rotated-2
  [[1 1 0 0]
   [1 0 0 0]
   [1 0 0 0]
   [0 0 0 0]])

(def j-block-rotated-3
  [[1 1 1 0]
   [0 0 1 0]
   [0 0 0 0]
   [0 0 0 0]])

(def i-block
  [[1 0 0 0]
   [1 0 0 0]
   [1 0 0 0]
   [1 0 0 0]])

(def i-block-rotated
  [[1 1 1 1]
   [0 0 0 0]
   [0 0 0 0]
   [0 0 0 0]])

(def blocks [z-block s-block t-block o-block l-block j-block i-block])
