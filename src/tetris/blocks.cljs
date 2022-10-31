(ns tetris.blocks
  (:require [tetris.tetromino :as tetromino]))

(defn block->string [block]
  (reduce (fn [acc next] (str acc "\n" next)) block))

(comment
  (block->string tetromino/i-block))

(defn rotate-block [block]
  ;; TODO: an "in-code" way to rotate (without transposing) would be better
  ;; case couldn't compare 2D vectors
  (cond
    (= block tetromino/z-block) tetromino/z-block-rotated
    (= block tetromino/z-block-rotated) tetromino/z-block
    (= block tetromino/s-block) tetromino/s-block-rotated
    (= block tetromino/s-block-rotated) tetromino/s-block

    (= block tetromino/o-block) tetromino/o-block

    (= block tetromino/t-block) tetromino/t-block-rotated-1
    (= block tetromino/t-block-rotated-1) tetromino/t-block-rotated-2
    (= block tetromino/t-block-rotated-2) tetromino/t-block-rotated-3
    (= block tetromino/t-block-rotated-3) tetromino/t-block

    (= block tetromino/l-block) tetromino/l-block-rotated-1
    (= block tetromino/l-block-rotated-1) tetromino/l-block-rotated-2
    (= block tetromino/l-block-rotated-2) tetromino/l-block-rotated-3
    (= block tetromino/l-block-rotated-3) tetromino/l-block

    (= block tetromino/j-block) tetromino/j-block-rotated-1
    (= block tetromino/j-block-rotated-1) tetromino/j-block-rotated-2
    (= block tetromino/j-block-rotated-2) tetromino/j-block-rotated-3
    (= block tetromino/j-block-rotated-3) tetromino/j-block

    (= block tetromino/i-block) tetromino/i-block-rotated
    (= block tetromino/i-block-rotated) tetromino/i-block))

(comment
  ;; Run `rotate-block`
  (rotate-block tetromino/t-block)
  ;; Run `rotate-block` twice
  (rotate-block (rotate-block tetromino/i-block))
  )
