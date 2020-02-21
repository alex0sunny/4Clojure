(fn finf [f & rs] 
  (let [[m n s t] rs
        [m n] [(or m 0) (or n 0)]
        f-seq (fn f-seq
                ([k] (f-seq k n))
                ([k p] (lazy-seq (cons (f k p) 
                                       (f-seq k (inc p))))))
        f2d (fn f2d 
              ([] (f2d m)) 
              ([l] (lazy-seq (cons (f-seq l)
                                   (f2d (inc l))))))]
    (if s (take s (map #(take t %) (f2d))) (f2d))))
