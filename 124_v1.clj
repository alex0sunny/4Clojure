(defn f-reversi [brd wb]
  (let [bw (if (= wb 'w) 'b 'w) 
        rg (range 4)
        f-ds-prs #(map (partial apply (partial map list))
                       [[rg %] [rg (rest %)] [(rest rg) %]])
        cs-prs (map #(map list rg (repeat %)) rg)
        ls-prs (concat cs-prs (map (partial map reverse) cs-prs)
                       (f-ds-prs rg) (f-ds-prs (reverse rg)))
        combs-prs (mapcat #(list % (rest %) (butlast %)) ls-prs)
        f-line #(hash-map :prs %2 :dat (map (partial get-in %) %2))
        f-lines #(map (partial f-line %) combs-prs)
        prs (filter #(= 'e (get-in brd %)) (mapcat #(map (partial list %) rg) rg))
        f-ck-lm (fn [{prs :prs dat :dat}] 
                  (if (#{[wb bw wb] [wb bw bw wb]} dat) (butlast (rest prs))))
        f-ck-move #(mapcat f-ck-lm (f-lines (assoc-in brd % wb)))]
    (reduce (fn [m p] (#(if (empty? %) m (assoc m p (set %))) (f-ck-move p))) 
            {} prs)))
