(def brd '[[e e e e]
           [e w b e]
           [w w b e]
           [e e b e]])
(def wb 'w)
(def bw (if (= wb 'w) 'b 'w))
(def rg (range 4))
(def f-ds-prs #(map (partial apply (partial map list))
                    [[rg %] [rg (rest %)] [(rest rg) %]]))
(def ds-prs (mapcat f-ds-prs [rg (reverse rg)]))
(def cs-prs (map #(map list rg (repeat %)) rg))
(def rs-prs (map (partial map reverse) cs-prs))
(def ls-prs (concat rs-prs cs-prs ds-prs))
(defn f-line [brd prs] (hash-map :prs prs :dat (map (partial get-in brd) prs)))

(def f-line #(hash-map :prs %2 :dat (map (partial get-in %) %2)))

(defn f-lines [brd] (map (partial f-line brd) ls-prs))

(def f-lines #(map (partial f-line %) ls-prs))

(def prs (filter #(= 'e (get-in brd %)) (mapcat #(map (partial list %) rg) rg)))

;;(defn f-ck-lm [{prs :prs dat :dat}] 
;;	(let [[f] (filter #(#{[wb bw wb] [wb bw bw wb]} (% dat))
;;	                  [identity rest butlast])] 
;;      (if f (butlast (rest (f prs))))))

;; f-cl-lm probably the core function of the solution. Take the line map -- this map has :prs and :dat keys. The :prs key correcopnds to indicies pairs for each cell of the line and the :dat key corresponds to the letters in line cells.
(defn f-ck-lm [{prs :prs dat :dat}] 
     (#(if % (butlast (rest (% prs))))
       (first (filter #(#{[wb bw wb] [wb bw bw wb]} (% dat)) 
                       [identity rest butlast]))))

(defn f-ck-move [pair] (mapcat f-ck-lm (f-lines (assoc-in brd pair wb))))

(def res (reduce (fn [m p] (#(if (empty? %) m (assoc m p (set %))) 
                             (f-ck-move p))) {} prs))

(println prs)
(println (f-ck-move (nth prs 3)))
(println res)
