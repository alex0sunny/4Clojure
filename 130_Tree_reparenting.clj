;Every node of a tree is connected to each of its children as well as its parent. One can imagine grabbing one node of a tree and dragging it up to the root position, leaving all connections intact. For example, below on the left is a binary tree. By pulling the "c" node up to the root, we obtain the tree on the right.
;Note it is no longer binary as "c" had three connections total -- two children and one parent. Each node is represented as a vector, which always has at least one element giving the name of the node as a symbol. Subsequent items in the vector represent the children of the node. Because the children are ordered it's important that the tree you return keeps the children of each node in order and that the old parent node, if any, is appended on the right. Your function will be given two args -- the name of the node that should become the new root, and the tree to transform. 
(fn repar [node [f & r :as tr]] 
  (if (= f node) tr
      (let [sub-tr (some #(and (get (set (flatten %)) node) %) r)
            rst (filter (partial not= sub-tr) tr)]
          (repar node (concat sub-tr (list rst))))))
