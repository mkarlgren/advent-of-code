(require '[clojure.java.io :as io])

(defn reader [file] (io/reader (str (.getParent (io/file *file*)) "/" file)))


(defn overlap [sections]
  (and
   (<= (nth sections 0) (nth sections 3))
   (<= (nth sections 2) (nth sections 1))))

(with-open
 [r (reader "04_input.txt")]
  (->>
   (line-seq r)
   (filter
    (fn [pair-assignments]
      (->>
       (re-seq #"\d+" pair-assignments)
       (map #(Long/parseLong %))
       (overlap))))
   (count)))
