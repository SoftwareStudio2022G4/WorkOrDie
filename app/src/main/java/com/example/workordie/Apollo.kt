package com.example.graphql

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("http://b477-140-114-221-77.ngrok.io/graphql")
    .build()