(fn [cs] 
  (let [fcs (fn [[s r]] 
              {:suit ({\S :spade \H :heart \D :diamond \C :club} s)
               :rank ((into {} (map vector "23456789TJQKA" (range))) r)})
        ms (map fcs cs)
        rks (map :rank ms)
        fs (vals (frequencies rks))
        [pair two-pair] (filter #(= 2 %) fs)
        cnt ((fn [rks] (->> (range 2 (inc (count rks)))
                            (mapcat #(partition % 1 (sort rks)))
                            (filter #(apply = (map - % (range))))
                            (cons [])
                            (apply max-key count)
                            count)) rks)
        straight (or (= 5 cnt) (= (sort rks) [0 1 2 3 12]))
        flush_ (not (second (set (map :suit ms))))]
    (cond (and straight flush_) :straight-flush
          ((set fs) 4) :four-of-a-kind
          (= 2 (count fs)) :full-house
          flush_ :flush
          straight :straight
          ((set fs) 3) :three-of-a-kind
          two-pair :two-pair
          pair :pair
          :else :high-card)))
