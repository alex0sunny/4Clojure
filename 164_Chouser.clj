;; The remake of Chouser's great solution.
(fn f [{c :accepts t :transitions q :q :as m 
        :or {q #{["" (:start m)]}}}]
  (lazy-seq (if-let [[[p s]] (seq q)]
              (let [q (into (disj q [p s])
                        (for [[a n] (t s)] [(str p a) n]))]
                (#(if (c s) (cons p %) %) (f (assoc m :q q)))))))
