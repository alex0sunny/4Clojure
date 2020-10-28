(fn fs [& ss] 
  (let [n-min   (apply min (map first ss))
        s-min   (mapcat (partial take-while (partial = n-min)) ss)
        ss-next  (map (partial drop-while #(= n-min %)) ss)]
    (if (= (count s-min) (count ss)) n-min (apply fs ss-next))))
