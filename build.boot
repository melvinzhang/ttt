#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.1"

(set-env!
  :project      'tic-tac-toe
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[[tailrecursion/boot.task   "2.2.4"]
                  [tailrecursion/hoplon      "5.10.25"]]
  :out-path     "resources/public"
  :src-paths    #{"src"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require
  '[tailrecursion.hoplon.boot    :refer :all]
  '[tailrecursion.boot.task.ring :refer [dev-server]])

(deftask development
  "Build tic-tac-toe for development."
  []
  (comp (watch) (hoplon {:pretty-print true :prerender false}) (dev-server)))

(deftask dev-debug
  "Build tic-tac-toe for development with source maps."
  []
  (comp (watch) (hoplon {:pretty-print true
                         :prerender false
                         :source-map true}) (dev-server)))

(deftask production
  "Build tic-tac-toe for production."
  []
  (hoplon {:optimizations :advanced}))
