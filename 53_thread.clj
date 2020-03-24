(fn f [s]
  (let [fr #(apply < %)
        ff (complement fr)
        fsp (fn [[_ p]] (split-with fr (drop-while ff p)))]
    (->>  
      (iterate fsp [[] (partition 2 1 s)])
      (take-while #(not= [[][]] %))
      (#(reverse (map first %)))
      (#(distinct (flatten (apply max-key count %)))))))
