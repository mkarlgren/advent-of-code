(require 'clojure.java.io)

(defn reader [file] (clojure.java.io/reader (str (.getParent (clojure.java.io/file *file*)) "/" file)))


(with-open
 [r (reader "01_input.txt")]
  (->>
   (line-seq r)
   (reduce
    (fn [curr-max cal-string]
      (if (= "" cal-string)
        [0 (apply max curr-max)]
        (update curr-max 0 + (Long/parseLong cal-string))))
    [0 0])
   (apply max)))
