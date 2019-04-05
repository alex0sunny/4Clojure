(defn fkarn [in] 
  (let [[D U I SU] [clojure.set/difference clojure.set/union
                    clojure.set/intersection clojure.set/superset?]
        fl (fn [sym] (symbol (clojure.string/lower-case (str sym))))
        ftr (fn [si so] (#(if (and (= 2 (count %)) (= 1 (count (set (map fl %))))) 
                              [so (apply disj si %)]) (D (U si so) (I si so))))
        fwr (fn [si ss] (#(if (some (partial clojure.set/superset? si) %) % ss)
                          (disj ss si)))
        fck (fn [si ss] (#(if (not= % ss) %) 
                              (let [sst (set (mapcat #(ftr si %) ss))]
                                (if-not (empty? sst) (U (disj ss si) sst) (fwr si ss)))))
        ssr ((fn fre [ss] (if-let [ssn (some #(fck % ss) ss)] (fre ssn) ss)) in)
        fcp (fn [ss] (every? (fn [s] (some #(SU s %) ss)) in))
        ffin (fn [ss] (some #(if (fcp %) %) (map (partial disj ss) ss)))]
    (last (take-while identity (iterate ffin ssr)))))
