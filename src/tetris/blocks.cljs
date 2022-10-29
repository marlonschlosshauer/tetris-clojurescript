(ns tetris.blocks
  (:require [tetris.tetromino :as tetromino]))

(defn block->string [block]
  (reduce (fn [acc next] (str acc "\n" next)) block))

(comment
 (block->string tetromino/i-block))





