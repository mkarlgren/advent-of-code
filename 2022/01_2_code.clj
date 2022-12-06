(require 'clojure.string 'clojure.java.io)

(defn reader [file] (clojure.java.io/reader (str (.getParent (clojure.java.io/file *file*)) "/" file)))


(with-open
 [r (reader "01_input.txt")]
  (->>
   (line-seq r)
   (reduce
    (fn [curr-max cal-string]
      (if (= "" cal-string)
        (conj curr-max 0)
        (cons
         (+ (first curr-max) (Long/parseLong cal-string))
         (rest curr-max))))
    '(0))
   (sort >)
   (take 3)
   (apply +)))
