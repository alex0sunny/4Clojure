(fn [v]
  (let [l (apply max (map count v)) w (count v)
        fw (fn [r] (map #(take % (repeat nil)) (range (- (inc l) (count r)))))
        fr (fn [r] (apply (partial map #(concat % r %2)) 
                          (#(list % (reverse %)) (fw r))))
        fre (fn [vs rs] (mapcat (fn [r] (map #(concat % [r]) vs)) rs))
        als (#(reduce fre (map list (first %)) (rest %)) (map fr v))
        frecs 
          (fn [al]
            (set
              (filter
                #(apply = (map sort (concat % (apply map list %))))
                  (mapcat identity
                    (for [i (butlast (range w)) j (butlast (range l))
                          :when (nth (nth al i) j)]
                      (for [le (range 1 (min (- l j) (- w i)))
                            :let [rec (map #(map (partial nth (nth al %)) 
                                                 (range j (+ j le 1))) 
                                           (range i (+ i le 1)))]
                            :while (every? identity (flatten rec))] rec))))))]
    (frequencies (map count (reduce into #{} (map frecs als))))))
