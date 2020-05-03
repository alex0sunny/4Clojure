  #(if (empty? %) []
       (->> (sort (set %))
            ((fn [[f :as s]] (range f (inc (last s)))))
            (map (set %))
            (partition-by nil?)
            (filter first)
            (map (fn [[f :as s]] [f (last s)]))))
