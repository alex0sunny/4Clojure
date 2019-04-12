(fn fkarn [in] 
  (let [fl (fn [ss] (set (map #(symbol (.toLowerCase (str %))) ss)))
        ftr (fn [si so] (#(if (= 1 (count (fl %))) (apply disj si %)) 
                          (into (apply disj si so) (apply disj so si))))
        fck (fn [ss si] (#(if (not-empty %) (into (disj ss si) %) ss) 
                          (set (filter identity (map (partial ftr si) ss)))))
        fte #(last (take-while identity (iterate % %2)))
        ssr (fte (fn [ss] (#(if (not= ss %) %) (reduce fck ss ss))) in)
        fcp (fn [ss] (every? (fn [s] (some #(empty? (apply disj % s)) ss)) in))] 
    (fte (fn [ss] (some #(if (fcp %) %) (map (partial disj ss) ss))) ssr)))
