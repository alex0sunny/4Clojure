(fn f [n] 
  (let [s (map #(- (int %) 48) (str n))
        l (quot (count s) 2)]
    (apply = (map #(apply + (% l s)) [take take-last]))))
