{{/* Expand the name of the chart. */}}
{{- define "storefront.name" -}}
{{- .Values.nameOverride | default .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/* Full name used for most resources. */}}
{{- define "storefront.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- .Values.nameOverride | default .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}

{{/* Common labels */}}
{{- define "storefront.labels" -}}
app.kubernetes.io/name: {{ include "storefront.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/part-of: icecream
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{/* Selector labels */}}
{{- define "storefront.selectorLabels" -}}
app.kubernetes.io/name: {{ include "storefront.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{/* Service account name */}}
{{- define "storefront.serviceAccountName" -}}
{{- if .Values.serviceAccount.create -}}
{{ default (include "storefront.fullname" .) .Values.serviceAccount.name }}
{{- else -}}
{{ default "default" .Values.serviceAccount.name }}
{{- end -}}
{{- end -}}
