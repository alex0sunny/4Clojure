;Crossword puzzle
 
;Difficulty:	Hard
;Topics:	game


;Write a function that takes a string and a partially-filled crossword puzzle board, and determines if the input string can be legally placed onto the board.

;The crossword puzzle board consists of a collection of partially-filled rows. Empty spaces are denoted with an underscore (_), unusable spaces are denoted with a hash symbol (#), and pre-filled spaces have a character in place; the whitespace characters are for legibility and should be ignored.

;For a word to be legally placed on the board:
;- It may use empty spaces (underscores)
;- It may use but must not conflict with any pre-filled characters.
;- It must not use any unusable spaces (hashes).
;- There must be no empty spaces (underscores) or extra characters before or after the word (the word may be bound by unusable spaces though).
;- Characters are not case-sensitive.
;- Words may be placed vertically (proceeding top-down only), or horizontally (proceeding left-right only).

(fn f[w s] 
  (let [sq (map (partial filter (partial not= \space)) s)
        seqs (concat sq (apply map vector sq))
        split-fn 
          (fn split-fn [s]
            (cond
              (empty? s)        nil
              (= \# (first s))  (split-fn (rest s))
              (= \# (last s))   (split-fn (butlast s))
              (every? (partial not= \#) s) (list s)
              :else (cons (take-while (partial not= \#) s)
                          (split-fn (drop-while (partial not= \#) s)))))
        strs (mapcat split-fn seqs)
        mk-pattern 
          (fn mk-pattern [s] 
            (concat "^" (map #(if (= % \_) "\\w" 
                                  (str \[ % \])) s) "$"))
        pattern-strs (map (partial apply str) (map mk-pattern strs))
        pattern-str (clojure.string/join "|" pattern-strs)]
      (not= nil (re-find (re-pattern pattern-str) w))))
