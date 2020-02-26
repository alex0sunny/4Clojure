(fn fnx [n] 
  (let [fd (fn [x] (map #(- (int %) 48) (str x)))
        fi #(Long. (apply str %))
        ds (fd n)
        l (count ds)
        lh (quot (dec l) 2)
        m (nth ds lh)
        dh (take lh ds)
        fm #(if (odd? l) [%] [% %])
        nn (fi (concat dh (fm m) (reverse dh)))]
    (#(if (< nn n) % (cons nn %))
      (lazy-seq
        (fnx
          (cond       
            (apply = 9 (fd nn)) (+ nn 2)
            (= 9 m) (#(fi (concat % (fm 0) (reverse %)))
                      (fd (inc (fi dh))))
            :else (fi (concat dh (map inc (fm m)) 
                              (reverse dh)))))))))
