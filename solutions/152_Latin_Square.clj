;; Latin Square Slicing
;; Difficulty:	Hard
;; Topics:	data-analysis math


;; A Latin square of order n is an n x n array that contains n different elements, each occurring exactly once in each row, and exactly once in each column. For example, among the following arrays only the first one forms a Latin square:

;; A B C    A B C    A B C

;; B C A    B C A    B D A

;; C A B    C A C    C A B

 

;; Let V be a vector of such vectors1 that they may differ in length2. We will say that an arrangement of vectors of V in consecutive rows is an alignment (of vectors) of V if the following conditions are satisfied:

;;     All vectors of V are used.
;;     Each row contains just one vector.
;;     The order of V is preserved.
;;     All vectors of maximal length are horizontally aligned each other.
;;     If a vector is not of maximal length then all its elements are aligned with elements of some subvector of a vector of maximal length.

;; Let L denote a Latin square of order 2 or greater. We will say that L is included in V or that V includes L iff there exists an alignment of V such that contains a subsquare that is equal to L.

;; For example, if V equals [[1 2 3][2 3 1 2 1][3 1 2]] then there are nine alignments of V (brackets omitted):

;;         1              2              3

 

;;       1 2 3          1 2 3          1 2 3

;;   A   2 3 1 2 1    2 3 1 2 1    2 3 1 2 1

;;       3 1 2        3 1 2        3 1 2

 

;;       1 2 3          1 2 3          1 2 3

;;   B   2 3 1 2 1    2 3 1 2 1    2 3 1 2 1

;;         3 1 2        3 1 2        3 1 2

 

;;       1 2 3          1 2 3          1 2 3

;;   C   2 3 1 2 1    2 3 1 2 1    2 3 1 2 1

;;           3 1 2        3 1 2        3 1 2

 

;; Alignment A1 contains Latin square [[1 2 3][2 3 1][3 1 2]], alignments A2, A3, B1, B2, B3 contain no Latin squares, and alignments C1, C2, C3 contain [[2 1][1 2]]. Thus in this case V includes one Latin square of order 3 and one of order 2 which is included three times.

;; Our aim is to implement a function which accepts a vector of vectors V as an argument, and returns a map which keys and values are integers. Each key should be the order of a Latin square included in V, and its value a count of different Latin squares of that order included in V. If V does not include any Latin squares an empty map should be returned. In the previous example the correct output of such a function is {3 1, 2 1} and not {3 1, 2 3}.

;; 1 Of course, we can consider sequences instead of vectors.
;; 2 Length of a vector is the number of elements in the vector. 

;; SOLUTION
;; General approach. Represent each alignment as a nil containing rectangle.
;; Get all possible squares from each alignment without nils.
;; Then filter Latin squares. From the result sequence make the set of the Latin squares.
;; From this set with 'count' and 'frequencies' get the required map.
;; l -- maximum row len
;; w -- column len for the rectangles to be constructed
;; fr -- returns all possible nil-wrappings for the original row 'r'
;; fre -- compliments partially contructed alignments 'vs' with possible next row 
;;        variants 'rs'
;; als -- possible alignments
;; flat -- checks whether the current square is Latin
;; fsqs -- from a given alignment 'al' returns all contained latin squares.
;;         For each possible i, j upper-left corner we get all possible squares. To
;;         avoid timeout error we first get maximum possible square with the set
;;         upper-left corner and then successively remove last column and last row.
;;         During this operation we filter out nil containing squares.
;; last line -- get Latin squares from all alignments into single set, 
;;              then from that set with 'count' and 'frequencies' get the final result.

(fn [v]
  (let [l (apply max (map count v)) w (count v)
        fr (fn [r] (let [lr (- l (count r)) sn (repeat nil)]
                     (map #(concat (take % sn) r (take (- lr %) sn)) (range (inc lr)))))
        fre (fn [vs rs] (mapcat (fn [r] (map #(concat % [r]) vs)) rs))
        als (#(reduce fre (map list (first %)) (rest %)) (map fr v))
        flat #(apply = (map sort (concat % (apply map list %) [(set (first %))])))
        fsqs (fn [al] (mapcat #(filter flat %)
                        (for [i (butlast (range w)) j (butlast (range l))]
                          (take-while #(> (count %) 1) 
                            (drop-while #(some nil? (flatten %))
                              (iterate #(butlast (map butlast %)) 
                                ( #(take % (map (partial take %)             
                                             (map (partial drop j) (drop i al))))
                                  (min (- l j) (- w i)) )))))))]
    (frequencies (map count (reduce into #{} (map fsqs als))))))

