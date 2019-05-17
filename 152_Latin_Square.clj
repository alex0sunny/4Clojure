(fn [v]
  (let [l (apply max (map count v)) w (count v)
        fw (fn [r] (map #(take % (repeat nil)) (range (- (inc l) (count r)))))
        fr (fn [r] 
             (apply (partial map #(concat % r %2)) (#(list % (reverse %)) (fw r))))
        fre (fn [vs rs] (mapcat (fn [r] (map #(concat % [r]) vs)) rs))
        als (#(reduce fre (map list (first %)) (rest %)) (map fr v))
        fsqs (fn [al]
                (filter #(apply = (map sort (concat % (apply map list %))))
                  (mapcat identity
                    (for [i (butlast (range w)) j (butlast (range l))]
                      (take-while #(> (count %) 1) 
                        (drop-while #(some nil? (flatten %))
                          (iterate #(butlast (map butlast %)) 
                            (#(take % (map (partial take %) 
                                        (map (partial drop j) (drop i al)))) 
                             (min (- l j) (- w i))))))))))]
    (frequencies (map count (reduce into #{} (map fsqs als))))))

