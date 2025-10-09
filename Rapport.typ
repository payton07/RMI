#import "@preview/modern-report-umfds:0.1.2": umfds

#let template(
  titre: [RAPPORT TP1 : \ ARCHITECTURE LOGICIELLES DISTRIBUÉES
  ],
  auteur: [Personne],
  doc,
) = {
  show: umfds.with(
    title: titre,
    authors: (auteur,),
    date: datetime.today().display("[day] [month repr:long] [year]"),
    lang: "fr",
  )
  doc
}

#let table-n(n: 5, images: (), ..doc) = {
  set table(columns: (for _ in range(n) { (1fr,) }), align: center, inset: 10pt, )
  let pos = doc.pos()
  table(..for e in range(pos.len()){
    (figure(pos.at(e), caption: [#images.at(e, default: "Image")]),)
  })
}

#show: template.with(
  titre: [RAPPORT TP1 : \ ARCHITECTURE LOGICIELLES DISTRIBUÉES
  ], auteur: "Patrice KEGLO"
)

#text(20pt)[#align(center)[INTRODUCTION ]]

#pagebreak()
Idée : programation RMI de façon generale , parler de ce proget , decrire les proget 
#text(20pt)[#align(center)[Partie 1 : Version simple ]]