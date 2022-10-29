(ns tetris.tetromino)

(def z-block
  [[1 1 0 0]
   [0 1 1 0]
   [0 0 0 0]
   [0 0 0 0]])

(def s-block
  [[0 1 1 0]
   [1 1 0 0]
   [0 0 0 0]
   [0 0 0 0]])

(def t-block
  [[1 1 1 0]
   [0 1 0 0]
   [0 0 0 0]
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

(def j-block
  [[0 1 0 0]
   [0 1 0 0]
   [1 1 0 0]
   [0 0 0 0]])

(def i-block
  [[1 0 0 0]
   [1 0 0 0]
   [1 0 0 0]
   [1 0 0 0]])

(def blocks [z-block s-block t-block o-block l-block j-block i-block])
