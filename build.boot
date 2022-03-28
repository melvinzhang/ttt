(set-env!
  :dependencies '[[adzerk/boot-cljs          "RELEASE"]
                  [adzerk/boot-reload        "RELEASE"]
                  [hoplon/boot-hoplon        "RELEASE"]
                  [hoplon/hoplon             "RELEASE"]
                  [org.clojure/clojure       "RELEASE"]
                  [org.clojure/clojurescript "RELEASE"]
                  [pandeiro/boot-http        "RELEASE"]
                  [prismatic/dommy           "RELEASE"]]
  :source-paths #{"src"}
  :asset-paths  #{"assets"})

(require
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  '[pandeiro.boot-http       :refer [serve]])

(deftask dev
  "Build ttt2 for local development."
  []
  (comp
    (serve)
    (watch)
    (reload)
    (hoplon)
    (cljs)))

(deftask prod
  "Build ttt2 for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (prerender)
    (target)))
