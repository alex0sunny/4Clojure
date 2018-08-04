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
(defn f-lines [brd ls-prs] (map (partial f-line brd) ls-prs))
(def lmaps (f-lines brd ls-prs))
(def prs (filter #(= 'e (get-in brd %)) (mapcat #(map (partial list %) rg) rg)))
(defn f-ckl [{prs :prs dat :dat}] 
	(let [comb (some #(combs %) dat)
	      ps (if (= 3 (count comb)) (take-last 3 prs) prs)]
	     (if comb
