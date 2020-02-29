(fn fnx [n] 
  (let [fd (fn [x] (map #(- (int %) 48) (str x)))
        fi #(Long. (apply str %))
        l (count (fd n))
        lp (quot (inc l) 2)
        p (take lp (fd n))
        fc #(fi (concat 
                  % ((if (odd? l) rest identity) (reverse %))))
        nn (fc p)]
    (concat (if (>= nn n) [nn])
            (lazy-seq (fnx (if (apply = 9 p) (+ nn 2)
                               (fc (fd (inc (fi p))))))))))
