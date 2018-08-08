(def brd '[[e e e e]
           [e w b e]
           [w w b e]
           [e e b e]])
(def wb 'w)
(def bw (if (= wb 'w) 'b 'w))
(def combs #{[wb bw wb] [wb bw bw wb]})
(def rg (range 4))
(def rg-s (set rg))
(def f-ds-prs #(map (partial apply (partial map list))
                    [[rg %] [rg (rest %)] [(rest rg) %]]))
(def ds-prs (mapcat f-ds-prs [rg (reverse rg)]))
(def cs-prs (map #(map list rg (repeat %)) rg))
(def rs-prs (map (partial map reverse) cs-prs))
(def ls-prs (concat rs-prs cs-prs ds-prs))
(defn f-line [brd prs] (hash-map :prs prs :dat (map (partial get-in brd) prs)))
(defn f-lines [brd] (map (partial f-line brd) ls-prs))
(def prs (filter #(= 'e (get-in brd %)) (mapcat #(map (partial list %) rg) rg)))
(defn f-ck-lm [{prs :prs dat :dat}] 
	(let [f (some #(if (#{[wb bw wb] [wb bw bw wb]} (% dat)) %)
	              [identity rest butlast])] 
      	     (if f (butlast (rest (f prs))))))
(defn f-ck-move [pair] 
  (let [ls-maps (f-lines (assoc-in brd pair wb))] (mapcat f-ck-lm ls-maps)))
(def res (reduce (fn [m p] (#(if (empty? %) m (assoc m p (set %))) 
                             (f-ck-move p))) {} prs))

(println prs)
