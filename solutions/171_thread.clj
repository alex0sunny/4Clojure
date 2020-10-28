#(if (empty? %) []
     (->> (range (apply min %) (inc (apply max %)))
          (map (set %))
          (partition-by nil?)
          (filter first)
          (map (fn [[f :as s]] [f (last s)]))))
