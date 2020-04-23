;;(fn [n a b] 
;;  (letfn [(f [n d] (#(/ (* d % (dec %)) 2) (+ 1N (quot (dec n) d))))] 
;;    (apply #(- (+ % %2) %3) (map #(f n %) [a b (* a b)]))))

(fn [n a b] 
  (letfn [(f [n d] (#(* 1/2 d % (inc %)) (quot (dec n) d)))] 
    (apply #(- (+ % %2) %3) (map #(f n %) [a b (* a b)]))))


