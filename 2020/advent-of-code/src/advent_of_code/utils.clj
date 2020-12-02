(ns advent-of-code.utils
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn read-csv [input-file]
  (with-open [reader (io/reader input-file)]
    (doall (csv/read-csv reader))))