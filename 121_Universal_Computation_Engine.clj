;; Universal Computation Engine
;; Difficulty:	Medium
;; Topics:	functions

;; Given a mathematical formula in prefix notation, return a function that calculates the value of the formula. The formula can contain nested calculations using the four basic mathematical operators, numeric constants, and symbols representing variables. The returned function has to accept a single parameter containing the map of variable names to their values.

partial
(fn fc [ex ps] 
  (if (coll? ex) 
    (apply ({'/ / '+ + '- - '* *} (first ex)) (map #(fc % ps) (rest ex)))
    (if (integer? ex) ex (ps ex))))
