(require '[clojure.java.io :as io])

(defn reader [file] (io/reader (str (.getParent (io/file *file*)) "/" file)))


(defn string-to-num [s] (first (.getBytes s)))
(def A-num (string-to-num "A"))
(def a-num (string-to-num "a"))

(defn shared-content [contents]
  (let [half-length (/ (.length contents) 2)
        first-half (subs contents 0 half-length)
        second-half (subs contents half-length)]
    (re-find (re-pattern (str "[" first-half "]")) second-half)))

(with-open
 [r (reader "03_input.txt")]
  (->>
   (line-seq r)
   (reduce
    (fn [priority-sum contents]
      (let [shared (shared-content contents)
            shared-num (string-to-num shared)
            priority (- shared-num (if (> shared-num a-num) a-num (- A-num 26)))]
        (+ 1 priority priority-sum)))
    0)))
