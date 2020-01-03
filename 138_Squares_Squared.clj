(fn [b e]
  (let [s (apply str (take-while #(<= % e) (iterate #(* % %) b)))
        [l] (drop-while #(< (* % %) (count s)) (range))
        els (#(butlast (interleave % %)) (range 1 (inc l)))
        es (map first 
             (rest (reductions (fn [[_ i] el] (split-at el i))
                 		   	       [nil (concat s (repeat "*"))] els)))
        fro #(vec (apply map vector (map reverse %)))
        sq0 (reduce #(cons %2 (fro %)) [[]] es)
        sq1 (map #(interpose " " %) (if (even? l) (fro (fro sq0)) sq0))
        lb (dec (+ l l))
        sq (vec (map vec (interpose (take lb (repeat " ")) sq1)))
        si (map #(- % (dec l)) [(dec lb) 0])
        sis (map #(map + [% %] si) (range lb))
        ds (for [si sis] (map #(or (get-in sq %) " ")
                           (map #(map + si [(- %) %]) (range lb))))]
    (vec (map #(apply str %) ds))))
