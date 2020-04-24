(fn [n a b] 
  (#(- (+ (% a) (% b)) (% (* a b)))
    (fn [d] (#(* 1/2 d % (inc %)) (quot (dec n) d)))))
