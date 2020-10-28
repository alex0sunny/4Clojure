(fn [[f & e :as s]] 
  (first 
    (sort-by count > 
      (filter #(not= 1 (count %))
        (reduce 
          (fn [r e] 
            (#(if (> e (last %)) 
		(concat (drop-last r) [(concat % [e])]) 
                (concat r [[e]])) (last r)))
          [[] [f]] e)))))
