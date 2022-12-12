(require '[clojure.java.io :as io])

(defn reader [file] (io/reader (str (.getParent (io/file *file*)) "/" file)))


(defn string-to-num [s] (first (.getBytes s)))
(def A-num (string-to-num "A"))
(def a-num (string-to-num "a"))

(defn shared-content [contents-seq]
  (reduce
   (fn [shared contents]
     (clojure.string/replace contents (re-pattern (str "[^" shared "]")) ""))
   contents-seq))

(with-open
 [r (reader "03_input.txt")]
  (->>
   (line-seq r)
   (partition 3)
   (reduce
    (fn [priority-sum group]
      (let [shared-num (string-to-num (shared-content group))
            priority (- shared-num (if (> shared-num a-num) a-num (- A-num 26)))]
        (+ 1 priority priority-sum)))
    0)))
