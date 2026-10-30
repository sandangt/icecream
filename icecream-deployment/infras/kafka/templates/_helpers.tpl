{{- define "kafka.name" -}}
{{- .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "kafka.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := include "kafka.name" . -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{- define "kafka.labels" -}}
helm.sh/chart: {{ printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{ include "kafka.selectorLabels" . }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{- define "kafka.selectorLabels" -}}
app.kubernetes.io/name: {{ include "kafka.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{- define "kafka.bootstrap" -}}
{{- printf "%s.%s.svc.%s:%v" (include "kafka.fullname" .) .Release.Namespace .Values.config.clusterDomain .Values.service.port -}}
{{- end -}}

{{/* Effective replication factor */}}
{{- define "kafka.rf" -}}
{{- if kindIs "invalid" .Values.config.replicationFactor -}}
{{- min (int .Values.replicaCount) 3 -}}
{{- else -}}
{{- .Values.config.replicationFactor -}}
{{- end -}}
{{- end -}}

{{/* Effective min.insync.replicas */}}
{{- define "kafka.isr" -}}
{{- if kindIs "invalid" .Values.config.minInsyncReplicas -}}
{{- if ge (int (include "kafka.rf" .)) 2 -}}2{{- else -}}1{{- end -}}
{{- else -}}
{{- .Values.config.minInsyncReplicas -}}
{{- end -}}
{{- end -}}
