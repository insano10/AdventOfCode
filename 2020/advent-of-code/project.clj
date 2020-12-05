(defproject advent-of-code "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/data.csv "0.1.4"]]
  :main ^:skip-aot advent-of-code.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repl-options {:init-ns advent-of-code.landing})
