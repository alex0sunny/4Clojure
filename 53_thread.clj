(fn [s]
  (let [fr #(apply < %)
        ff (complement fr)
        fsp (fn [p] (split-with fr (drop-while ff p)))]
    (->>  
      (iterate #(fsp (second %)) [[] (partition 2 1 s)])
      (take-while #(not= [[][]] %))
      (#(reverse (map first %)))
      (#(distinct (flatten (apply max-key count %)))))))
