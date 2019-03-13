(ns sakay-web-utils.geo
  (:require
   [clojure.spec.alpha :as s]
   [cuerdas.core :as cuerdas]
   #?@(:cljs [[goog.string :refer [format]]
              [goog.string.format]])))

(s/def ::location-format #{:latlng :lnglat})

(s/def ::latitude number?)

(s/def ::longitude number?)

(s/def ::location (s/keys :req-un [::latitude ::longitude]))

(defn location->string
  ([location]
   (location->string :latlng location))
  ([fmt {:keys [latitude longitude]}]
   (when (and latitude longitude)
     (if (= fmt :latlng)
       (format "%.06f,%.06f" latitude longitude)
       (format "%.06f,%.06f" longitude latitude)))))

(defn string->location
  "By default, assumes latlng. Can instead pass a format as a first argument.
  the format argument can either be :latlng or :lnglat.
  Returns a map with the keys :latitude and :longitude"
  ([loc-string]
   (string->location :latlng loc-string))
  ([fmt loc-string]
   (let [loc-vec   (-> loc-string
                       (cuerdas/split ",")
                       (cuerdas/trim))
         [lat lng] (if (= format :lnglat)
                     (reverse loc-vec)
                     loc-vec)]
     {:latitude  lat
      :longitude lng})))
