;; General approach. 
;;   Seek all possible adjacent terms for each term, if any, 
;;     replace the term being inspected with the found common parts.
;;     Repeat while there are adjacent terms.
;;   Remove possible excessive miniterms, if any.
;; fl  -- convert all symbols in a set to lower-case symbols
;; ftr -- check if 'si' and 'so' terms are adjacent, if they are, return
;;        common part.
;; fck -- check 'si' term against 'ss' set of terms. If there are adjacent
;;        terms, return set of terms without si term, but with common part
;;        terms added to 'ss' set.
;; fte -- aka-'generic' function. Apply some function to the argument, while
;;        the result is not nil.
;; ssr -- preliminary result. Replace adjacent terms with common parts until
;;        possible.
;; fcp -- post-check function. Check if every original term contains some
;;        miniterm of the candidate Karno map 'ss'.
;; last line -- successively disjoin candidate Karno map with each
;;        miniterm. If some disjoin gives new candidate Karno map, return it and
;;        then again check each miniterm whether it is excessive.
(fn fkarn [in] 
  (let [fl  (fn [ss] (set (map #(symbol (.toLowerCase (str %))) ss)))
        ftr (fn [si so] (#(if (= 1 (count (fl %))) (apply disj si %)) 
                          (into (apply disj si so) (apply disj so si))))
        fck (fn [ss si] (#(if (not-empty %) (into (disj ss si) %) ss) 
                          (set (filter identity (map (partial ftr si) ss)))))
        fte #(last (take-while identity (iterate % %2)))
        ssr (fte (fn [ss] (#(if (not= ss %) %) (reduce fck ss ss))) in)
        fcp (fn [ss] (every? (fn [s] (some #(empty? (apply disj % s)) ss)) in))] 
