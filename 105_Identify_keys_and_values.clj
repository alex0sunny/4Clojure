(fn reduce-fn 
    ([rmap [fs & rs :as s]]
             (if (empty? s) rmap
                (reduce-fn (assoc rmap fs (take-while integer? rs))
                           (drop-while integer? rs))))
    ([s] (reduce-fn {} s)))
