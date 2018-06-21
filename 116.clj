(fn fc [n]
  (let [fp? (fn [n s] (not (some #(zero? (mod n %)) s)))
        fp
          (fn fp [s] 
            (let [n (+ 2 (last s))] 
              (first (drop-while #(not (fp? % s)) 
                        (filter odd? (drop n (range)))))))
        f-primes 
          (fn f-primes [s] 
            (let [pn (fp s) sn (concat s (list pn))] 
              (cons pn (lazy-seq (f-primes sn)))))
        primes (#(concat % (f-primes %)) [2 3])
        f-part 
          (fn [n] (first (drop-while 
              #(> n (second %)) (partition 3 1 primes))))
        [a b c] (f-part n)]
    (and (= n b) (= n (/ (+ a c) 2)))))
